SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[Discounts](
	[DiscountId] [int] IDENTITY(1,1) NOT NULL,
	[DiscountAmount] [int] NOT NULL,
	[TourId] [int] NOT NULL,
 CONSTRAINT [PK_Discounts] PRIMARY KEY CLUSTERED 
(
	[DiscountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
ALTER TABLE [dbo].[Discounts]  WITH CHECK ADD  CONSTRAINT [FK_Discounts_Tours1] FOREIGN KEY([TourId])
REFERENCES [dbo].[Tours] ([TourId])
ON DELETE CASCADE
ALTER TABLE [dbo].[Discounts] CHECK CONSTRAINT [FK_Discounts_Tours1];